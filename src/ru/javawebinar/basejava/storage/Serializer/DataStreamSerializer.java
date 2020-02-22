package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void writeFile(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section s = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) s).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) s).getParagraphs(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        CompanySection cs = ((CompanySection) s);
                        writeCollection(dos, ((CompanySection) s).getCompanies(), company -> {
                            Link link = company.getHomePage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeCollection(dos, company.getPositions(), position -> {
                                serializeLocalDate(dos, position.getPeriodStart());
                                serializeLocalDate(dos, position.getPeriodEnd());
                                dos.writeUTF(position.getJobTitle());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            }
        }
    }

    @FunctionalInterface
    interface Process {
        void execute() throws IOException;
    }

    @FunctionalInterface
    interface ConsumerWithException<T> {
        void accept(T element) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> cl, ConsumerWithException<T> writer)
            throws IOException {
        dos.writeInt(cl.size());
        for (T elm : cl) {
            writer.accept(elm);
        }
    }

    @Override
    public Resume readFile(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readElements(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        readElements(dis, () -> list.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Company> companies = new ArrayList<>();
                        readElements(dis, () -> {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Company.Position> positions = new ArrayList<>();
                            readElements(dis, () -> positions.add(new Company.Position(
                                    LocalDate.of(dis.readInt(), dis.readInt(), 1),
                                    LocalDate.of(dis.readInt(), dis.readInt(), 1),
                                    dis.readUTF(),
                                    dis.readUTF()
                            )));
                            companies.add(new Company(link, positions));
                        });
                        resume.addSection(sectionType, new CompanySection(companies));
                        break;
                }
            });
            return resume;
        }
    }

    private void serializeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonthValue());
    }

    private void readElements(DataInputStream dis, Process process) throws IOException {
        int num = dis.readInt();
        for (int j = 0; j < num; j++) {
            process.execute();
        }
    }
}