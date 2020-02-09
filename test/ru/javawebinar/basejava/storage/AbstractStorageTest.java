package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String NEW_UUID = "New UUID";

    private final static String NAME_1 = "Dmitry";
    private final static String NAME_2 = "Alena";
    private final static String NAME_3 = "Sergey";
    private final static String NEW_Name = "Olga";

    private final static Resume R1;
    private final static Resume R2;
    private final static Resume R3;
    private final static Resume R4;

    static {
        R1 = new Resume(UUID_1, NAME_1);
        R2 = new Resume(UUID_2, NAME_2);
        R3 = new Resume(UUID_3, NAME_3);
        R4 = new Resume(NEW_UUID, NEW_Name);

        R1.addContact(ContactType.PHONE, "1234567");
        R1.addContact(ContactType.MAIL, "mail1.mail.ru");
        R1.addContact(ContactType.SKYPE, "skype1");

        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal 1"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection
                ("Achievement 1", "Achievement 2", "Achievement3"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection
                ("Java", "SQL", "JavaScript"));
        R1.addSection(SectionType.EXPERIENCE, new CompanySection(
                new Company("Company1", "HTTP//Company1.ru",
                        new Company.Position(2008, Month.JULY,
                                "position11", "description11"),
                        new Company.Position(2005, Month.JULY, 2008, Month.JULY,
                                "position12", "description12")),
                new Company("Company 2",
                        new Company.Position(2004, Month.JUNE, 2005, Month.JULY,
                                "position21", "description21"))));
        R1.addSection(SectionType.EDUCATION, new CompanySection(
                new Company("University 1",
                        new Company.Position(2001, Month.SEPTEMBER, 2005, Month.JUNE,
                                "aspirant ", "IT Faculty"),
                        new Company.Position(1996, Month.SEPTEMBER, 2001, Month.JULY,
                                "student","IT faculty"))));
        R2.addContact(ContactType.PHONE,"223322");
        R2.addContact(ContactType.MAIL,"mail2@ya.ru");
        R1.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Company3","HTTP//Company3.ru",
                                new Company.Position(2000, Month.NOVEMBER, 2002, Month.MAY,
                                        "technician",null))));


    }

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        Resume resumeTestSV = R4;
        storage.save(resumeTestSV);
        assertSize(4);
        Assert.assertEquals(resumeTestSV, storage.get(NEW_UUID));
    }

    @Test
    public void update() {
        Resume ResumeTestUD = new Resume(UUID_2, NAME_2);
        storage.update(ResumeTestUD);
        Assert.assertEquals(0, ResumeTestUD.compareTo(storage.get(UUID_2)));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test
    public void getAll() {
        Resume[] resumeTestGA = {R2, R1, R3};
        List<Resume> listTestGA = Arrays.asList(resumeTestGA);
        Resume[] resumeGA = storage.getAllSorted().toArray(new Resume[0]);
        Assert.assertArrayEquals(resumeTestGA, resumeGA);
        //TODO assertTrue(Arrays.equals(toSort, sortedInts));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NEW_UUID);
    }

    @Test
    public void get() {
        Assert.assertEquals(R2, storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NEW_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R4);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}