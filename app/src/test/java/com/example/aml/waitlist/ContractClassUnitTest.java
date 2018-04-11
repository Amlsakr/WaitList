package com.example.aml.waitlist;

import android.provider.BaseColumns;

import com.example.aml.waitlist.data.WaitlistContract;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ContractClassUnitTest {


    @Test
    public void inner_class_exists() throws Exception {
        Class[] innerClasses = WaitlistContract.class.getDeclaredClasses();
        assertEquals("There shoukd be 1 Inner Class inside the contract class" , 1
        , innerClasses.length);
    }


    @Test
    public void inner_class_type_correct () throws Exception {
        Class[] innerClasses = WaitlistContract.class.getDeclaredClasses();
        assertEquals("Can not find inner class to complete unit Test " , 1 , innerClasses.length);
        Class entryClass = innerClasses[0];
        assertTrue("Inner Class should implement the BaseColumns interface" , BaseColumns.class.isAssignableFrom(entryClass));
        assertTrue ("Inner Class Should be final" , Modifier.isFinal(entryClass.getModifiers()));
        assertTrue("Inner class should be static", Modifier.isStatic(entryClass.getModifiers()));
    }

@Test
   public  void inner_class_members_correct () throws  Exception {
    Class[] innerClasses = WaitlistContract.class.getDeclaredClasses();
    assertEquals("can not find inner class to complete unit test" , 1 , innerClasses.length);
    Class entryClass = innerClasses[0];
    Field[] allfields = entryClass.getDeclaredFields();
    assertEquals("There should be exactly 4 String members in the inner claa" , 4 , allfields.length);

    for (Field field : allfields) {
        assertTrue("All members in the contract  class should be Strings" , field.getType()== String.class);
        assertTrue("All members in the contract class should be final", Modifier.isFinal(field.getModifiers()));
        assertTrue("All members in the contract class should be Static" , Modifier.isStatic(field.getModifiers()));
    }
}

}