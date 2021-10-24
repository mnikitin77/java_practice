import com.mvnikitin.practice.lesson4.Lesson4Factory;
import com.mvnikitin.practice.lesson4.dao.StudentRepository;
import com.mvnikitin.practice.lesson4.dao.impl.StudentRepositoryImpl;
import com.mvnikitin.practice.lesson4.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class HomeworkHibernateTest {
    private static final int TEST_DATA_AMOUNT = 1_000;

    @AfterAll
    public static void tearDown() {
        Lesson4Factory.closeFactory();
    }

    @BeforeEach()
    public void fillData() {
        StudentRepository repo = new StudentRepositoryImpl();
        List<Student> students = new ArrayList<>(TEST_DATA_AMOUNT);
        for (int i = 0; i < TEST_DATA_AMOUNT; i++) {
            students.add(new Student("Ivan " + i, "mark_" + i));
        }
        repo.saveAll(students);
    }

    @AfterEach
    public void clear() {
        StudentRepository repo = new StudentRepositoryImpl();
        repo.deleteAll();
    }

    @Test
    @DisplayName("A new entity creation test")
    public void createTest() {
        StudentRepository repo = new StudentRepositoryImpl();

        Student saved = repo.save(new Student("Гайкин Пётр", "5"));
        Assertions.assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test of reading an entity")
    public void readTest() {
        StudentRepository repo = new StudentRepositoryImpl();

        Student expected = repo.save(new Student("Гайкин Пётр", "5"));
        Long id = expected.getId();
        Assertions.assertThat(id).isNotNull();

        Student actual = repo.findById(id);
        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(actual.getMark()).isEqualTo(expected.getMark());
    }

    @Test
    @DisplayName("Test of updating an entity")
    public void updateTest() {
        StudentRepository repo = new StudentRepositoryImpl();

        Student newOne = repo.save(new Student("Гайкин Пётр", "5"));
        Long id = newOne.getId();
        Assertions.assertThat(id).isNotNull();

        newOne.setName("Мочалкан Авдотий");
        newOne.setMark("4");
        Student updatedOne = repo.save(newOne);
        Student foundByIdOne = repo.findById(newOne.getId());

        Assertions.assertThat(foundByIdOne.getId()).isEqualTo(updatedOne.getId());
        Assertions.assertThat(foundByIdOne.getName()).isEqualTo(updatedOne.getName());
        Assertions.assertThat(foundByIdOne.getMark()).isEqualTo(updatedOne.getMark());
    }

    @Test
    @DisplayName("Entity delition test")
    public void deleteTest() {
        StudentRepository repo = new StudentRepositoryImpl();

        Student newOne = repo.save(new Student("Гайкин Пётр", "5"));
        Long id = newOne.getId();
        Assertions.assertThat(id).isNotNull();

        repo.deleteById(id);

        Student deletedOne = repo.findById(id);
        Assertions.assertThat(deletedOne).isNull();
    }
}
