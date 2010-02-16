package service;

import com.mits.core.client.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Stateless(name = "UserManager")
@Local(UserManager.class)
public class UserManagerBean implements UserManager {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String[] FIRST_NAMES = new String[]{
            "Michal", "Marcin", "Dominika", "Niedzwiadek", "Jimmy", "Cathy", "Barney", "Fred",
            "Eddie", "Carlos"};

    private static final String[] LAST_NAMES = new String[]{
            "Smith", "Jones", "Epps", "Gibbs", "Webber", "Blum", "Mendez",
            "Crutcher", "Needler", "Wilson", "Chase", "Edelstein"};

    private static final String[] SUBJECTS = new String[]{
            "Chemistry", "Phrenology", "Geometry", "Underwater Basket Weaving",
            "Basketball", "Computer Science", "Statistics", "Materials Engineering",
            "English Literature", "Geology"};


    private static final int CLASS_LENGTH_MINS = 50;

    private static final int MAX_SCHED_ENTRIES = 5;

    private static final int MIN_SCHED_ENTRIES = 1;

    private static final int MAX_PEOPLE = 100;

    private static final int STUDENTS_PER_PROF = 5;

    private final Random rnd = new Random(3);

    @PostConstruct
    public void initialize() {
        generateRandomPeople();
    }

    @SuppressWarnings("unchecked")
    public List<Person> getPeople(int start, int count) {
        return (List<Person>) entityManager.createQuery("select p from Person p")
                .setFirstResult(start)
                .setMaxResults(count).getResultList();
    }
    


    private void generateRandomPeople() {
        for (int i = 0; i < MAX_PEOPLE; ++i) {
            Person person = generateRandomPerson();
            entityManager.persist(person);
        }
    }

    private Person generateRandomPerson() {
        // 1 out of every so many people is a prof.
        //
        if (rnd.nextInt(STUDENTS_PER_PROF) == 1) {
            return generateRandomProfessor();
        } else {
            return generateRandomStudent();
        }
    }

    private Person generateRandomProfessor() {
        Professor prof = new Professor();

        String firstName = pickRandomString(FIRST_NAMES);
        String lastName = pickRandomString(LAST_NAMES);
        prof.setName("Dr. " + firstName + " " + lastName);

        String subject = pickRandomString(SUBJECTS);
        prof.setDescription("Professor of " + subject);

        generateRandomSchedule(prof.getTeachingSchedule());

        return prof;
    }

    private void generateRandomSchedule(Schedule sched) {
        int range = MAX_SCHED_ENTRIES - MIN_SCHED_ENTRIES + 1;
        int howMany = MIN_SCHED_ENTRIES + rnd.nextInt(range);

        TimeSlot[] timeSlots = new TimeSlot[howMany];

        for (int i = 0; i < howMany; ++i) {
            int startHrs = 8 + rnd.nextInt(9); // 8 am - 5 pm
            int startMins = 15 * rnd.nextInt(4); // on the hour or some quarter
            int dayOfWeek = 1 + rnd.nextInt(5); // Mon - Fri

            int absStartMins = 60 * startHrs + startMins; // convert to minutes
            int absStopMins = absStartMins + CLASS_LENGTH_MINS;

            timeSlots[i] = new TimeSlot(dayOfWeek, absStartMins, absStopMins);
        }

        Arrays.sort(timeSlots);

        for (int i = 0; i < howMany; ++i) {
            sched.addTimeSlot(timeSlots[i]);
        }
    }

    private Person generateRandomStudent() {
        Student student = new Student();

        String firstName = pickRandomString(FIRST_NAMES);
        String lastName = pickRandomString(LAST_NAMES);
        student.setName(firstName + " " + lastName);

        String subject = pickRandomString(SUBJECTS);
        student.setDescription("Majoring in " + subject);

        generateRandomSchedule(student.getClassSchedule());

        return student;
    }

    private String pickRandomString(String[] a) {
        int i = rnd.nextInt(a.length);
        return a[i];
    }


}
