package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1","Dmitry Ivanov" );
        Resume r2 = new Resume("uuid2","Alena Petrova" );
        Resume r3 = new Resume("uuid3", "Sergey Nikolaev");
        Resume r4 = new Resume("dummy", "Olga Nikitina");


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);


        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(r1);
        System.out.println("UPDATE r1");
        printAll();
        ARRAY_STORAGE.delete(r2.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
