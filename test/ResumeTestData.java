import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {

        String uuid = "uuid1";
        String fullName = "Григорий Кислин";
        Resume testResume = new Resume(uuid, fullName);

        String phone1 = "+7(921) 855-0482";
        String skype1 = "skype:grigory.kislin";
        String email1 = "gkislin@yandex.ru";

        testResume.addContact(ContactType.PHONE, phone1);
        testResume.addContact(ContactType.SKYPE, skype1);
        testResume.addContact(ContactType.MAIL, email1);

        String objective1 = "Ведущий стажировок и корпоративного обучения" +
                " по Java Web и Enterprise технологиям.\n";
        Section objectiveSection = new TextSection(objective1);
        testResume.addSection(SectionType.OBJECTIVE, objectiveSection);

        String personal1 = "Аналитический склад ума, сильная логика, креативность," +
                " инициативность. Пурист кода и архитектуры.\n";
        Section personalSection = new TextSection(personal1);
        testResume.addSection(SectionType.PERSONAL, personalSection);

        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX)." +
                " Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");

        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления " +
                "проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira," +
                " Zendesk.\n");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы" +
                " River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.\n");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке" +
                " технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock" +
                " для алгоритмического трейдинга.\n");

        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия" +
                " слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish)." +
                " Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios." +
                " Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы" +
                " России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        testResume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        List<String> qualification = new ArrayList<>();

        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce\n");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle\n");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB\n");
        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n");
        qualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n");
        qualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, " +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT," +
                " JUnit, Selenium (htmlelements).\n");
        qualification.add("Python: Django.\n");
        qualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n");
        qualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n");
        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail," +
                " JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet," +
                " HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\n");
        qualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,\n");
        qualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher," +
                " Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.\n");
        qualification.add("Отличное знание и опыт применения концепций ООП, SOA, " +
                "шаблонов проектрирования, архитектурных шаблонов, UML, " +
                "функционального программирования\n");
        qualification.add("Родной русский, английский \"upper intermediate\n");
        testResume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualification));

        List<Company> companies = new ArrayList<>();
        List<Company.Position> positions = new ArrayList<>();
        Company.Position position;
        String name;
        String url;
        LocalDate dateS;
        LocalDate dateE;
        String jobTitle;
        String description;

        name = "Java Online Projects";
        url = "http://javaops.ru/";
        dateS = LocalDate.of(2013, 10, 1);
        dateE = LocalDate.now();
        jobTitle = "Автор проекта.";
        description = "Создание, организация и проведение Java онлайн проектов и стажировок.";

        position = new Company.Position(dateS, dateE, jobTitle, description);
        positions.clear();
        positions.add(position);
        companies.add(new Company(new Link(name, url), positions));

        name = "Wrike";
        url = "https://www.wrike.com/";
        dateS = LocalDate.of(2014, 10, 1);
        dateE = LocalDate.of(2016, 1, 1);
        jobTitle = "Старший разработчик (backend)";
        description = "Проектирование и разработка онлайн платформы управления проектами " +
                "Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n";
        position = new Company.Position(dateS, dateE, jobTitle, description);
        positions.clear();
        positions.add(position);
        companies.add(new Company(new Link (name, url), positions));

        name = "RIT Center";
        url = "RIT Center";
        dateS = LocalDate.of(2012, 4, 1);
        dateE = LocalDate.of(2014, 10, 1);
        jobTitle = "Java архитектор";
        description = "Организация процесса разработки системы ERP для разных " +
                "окружений: релизная политика, версионирование, ведение CI (Jenkins)," +
                " миграция базы (кастомизация Flyway), конфигурирование системы" +
                " (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части " +
                "системы. Разработка интергационных сервисов: CMIS, BPMN2, " +
                "1C (WebServices), сервисов общего назначения (почта, экспорт в pdf," +
                " doc, html). Интеграция Alfresco JLAN для online редактирование из " +
                "браузера документов MS Office. Maven + plugin development, Ant," +
                " Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis," +
                " OpenCmis, Bonita, Python scripting, Unix shell remote scripting" +
                " via ssh tunnels, PL/Python\n";
        position = new Company.Position(dateS, dateE, jobTitle, description);
        positions.clear();
        positions.add(position);
        companies.add(new Company(new Link (name, url), positions));

        name = "Luxoft (Deutsche Bank)";
        url = "http://www.luxoft.ru/";
        dateS = LocalDate.of(2010, 12, 1);
        dateE = LocalDate.of(2012, 4, 1);
        jobTitle = "Ведущий программист";
        description = "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC," +
                " SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM." +
                " Реализация RIA-приложения для администрирования, мониторинга и анализа результатов" +
                " в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT)," +
                " Highstock, Commet, HTML5.";
        position = new Company.Position(dateS, dateE, jobTitle, description);
        positions.clear();
        positions.add(position);
        companies.add(new Company(new Link(name, url), positions));

        testResume.addSection(SectionType.EXPERIENCE, new CompanySection(companies));

        for (ContactType type : ContactType.values()
        ) {
            System.out.println(type.name() + " " + testResume.getContact(type));
        }

        for (SectionType type : SectionType.values()
        ) {
            System.out.println(type.name() + " " + testResume.getSection(type));
        }
    }
}