package javax.time.i18n;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.time.calendar.DateTimeFieldRule;

import org.testng.annotations.Test;

public class TestJapaneseChronology {

    @Test
    public void testGetName() {
        assertEquals(JapaneseChronology.INSTANCE.getName(), "Japanese");
    }
    
    @Test
    public void testConstructor() throws Exception {
        for (Constructor<?> constructor : JapaneseChronology.class.getDeclaredConstructors()) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        }
    }
    
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        Object chronology = JapaneseChronology.INSTANCE;
        assertTrue(chronology instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(chronology);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), chronology);
    }

    @Test
    public void testImmutable() throws Exception {
        Class<JapaneseChronology> cls = JapaneseChronology.class;
        assertTrue(Modifier.isPublic(cls.getModifiers()));
        assertTrue(Modifier.isFinal(cls.getModifiers()));
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) == false) {
                assertTrue(Modifier.isPrivate(field.getModifiers()));
                assertTrue(Modifier.isFinal(field.getModifiers()));
            }
        }
    }

    @Test
    public void testEra() throws Exception {
        DateTimeFieldRule<JapaneseEra> rule = JapaneseChronology.eraRule();
        assertEquals(rule.getID(), "Japanese.Era");
        assertEquals(rule.getName(), "Era");
        assertEquals(rule.getMinimumValue(), -3);
        assertEquals(rule.getLargestMinimumValue(), -3);
        assertEquals(rule.getMaximumValue(), 2);
        assertEquals(rule.getSmallestMaximumValue(), 2);
        assertEquals(rule.isFixedValueSet(), true);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodEras());
        assertEquals(rule.getPeriodRange(), null);
        serialize(rule);
    }

    @Test
    public void testYearOfEra() throws Exception {
        DateTimeFieldRule<Integer> rule = JapaneseChronology.yearOfEraRule();
        assertEquals(rule.getID(), "Japanese.YearOfEra");
        assertEquals(rule.getName(), "YearOfEra");
        assertEquals(rule.getMinimumValue(), JapaneseDate.MIN_YEAR_OF_ERA);
        assertEquals(rule.getLargestMinimumValue(), JapaneseDate.MIN_YEAR_OF_ERA);
        assertEquals(rule.getMaximumValue(), JapaneseDate.MAX_YEAR_OF_ERA);
        assertEquals(rule.getSmallestMaximumValue(), JapaneseDate.MAX_YEAR_OF_ERA);
        assertEquals(rule.isFixedValueSet(), true);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodYears());
        assertEquals(rule.getPeriodRange(), JapaneseChronology.periodEras());
        serialize(rule);
    }

    @Test
    public void testMonthOfYear() throws Exception {
        DateTimeFieldRule<Integer> rule = JapaneseChronology.monthOfYearRule();
        assertEquals(rule.getID(), "Japanese.MonthOfYear");
        assertEquals(rule.getName(), "MonthOfYear");
        assertEquals(rule.getMinimumValue(), 1);
        assertEquals(rule.getLargestMinimumValue(), 1);
        assertEquals(rule.getMaximumValue(), 12);
        assertEquals(rule.getSmallestMaximumValue(), 12);
        assertEquals(rule.isFixedValueSet(), true);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodMonths());
        assertEquals(rule.getPeriodRange(), JapaneseChronology.periodYears());
    }

    @Test
    public void testDayOfMonth() throws Exception {
        DateTimeFieldRule<Integer> rule = JapaneseChronology.dayOfMonthRule();
        assertEquals(rule.getID(), "Japanese.DayOfMonth");
        assertEquals(rule.getName(), "DayOfMonth");
        assertEquals(rule.getMinimumValue(), 1);
        assertEquals(rule.getLargestMinimumValue(), 1);
        assertEquals(rule.getMaximumValue(), 31);
        assertEquals(rule.getSmallestMaximumValue(), 28);
        assertEquals(rule.isFixedValueSet(), false);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodDays());
        assertEquals(rule.getPeriodRange(), JapaneseChronology.periodMonths());
        serialize(rule);
    }

    @Test
    public void testDayOfYear() throws Exception {
        DateTimeFieldRule<Integer> rule = JapaneseChronology.dayOfYearRule();
        assertEquals(rule.getID(), "Japanese.DayOfYear");
        assertEquals(rule.getName(), "DayOfYear");
        assertEquals(rule.getMinimumValue(), 1);
        assertEquals(rule.getLargestMinimumValue(), 1);
        assertEquals(rule.getMaximumValue(), 366);
        assertEquals(rule.getSmallestMaximumValue(), 365);
        assertEquals(rule.isFixedValueSet(), false);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodDays());
        assertEquals(rule.getPeriodRange(), JapaneseChronology.periodYears());
        serialize(rule);

    }

    @Test
    public void testDayOfWeek() throws Exception {
        DateTimeFieldRule<Integer> rule = JapaneseChronology.dayOfWeekRule();
        assertEquals(rule.getID(), "Japanese.DayOfWeek");
        assertEquals(rule.getName(), "DayOfWeek");
        assertEquals(rule.getMinimumValue(), 1);
        assertEquals(rule.getLargestMinimumValue(), 1);
        assertEquals(rule.getMaximumValue(), 7);
        assertEquals(rule.getSmallestMaximumValue(), 7);
        assertEquals(rule.isFixedValueSet(), true);
        assertEquals(rule.getPeriodUnit(), JapaneseChronology.periodDays());
        assertEquals(rule.getPeriodRange(), JapaneseChronology.periodWeeks());
        serialize(rule);
    }

    public void test_toString() throws Exception {
        assertEquals(JapaneseChronology.INSTANCE.toString(), "Japanese");
    }

    private void serialize(DateTimeFieldRule<?> rule) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rule);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), rule);
    }

}