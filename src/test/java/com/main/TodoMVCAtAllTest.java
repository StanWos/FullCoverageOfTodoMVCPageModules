package com.main;

import org.junit.Test;
import static pages.TodoMVC.*;
import static pages.TodoMVC.TaskType.*;

/**
 * Created by stan on 25.03.16.
 */
public class TodoMVCAtAllTest {

    @Test
    public void testCompleteAtAll() {
        givenAtAll(aTask("1", ACTIVE));

        toggle("1");
        assertTasks("1");
        assertItemLeft(0);
    }

    @Test
    public void testCompleteAllAtAll() {
        givenAtAll(ACTIVE, "1", "2");

        toggleAll();
        assertTasks("1", "2");
        assertItemLeft(0);
    }

    @Test
    public void testReopenAtAll() {
        givenAtAll(aTask("1", COMPLETED), aTask("2", ACTIVE));

        toggle("1");
        assertTasks("1", "2");
        assertItemLeft(2);
    }

    @Test
    public void testReopenAllAtAll() {
        givenAtAll(COMPLETED, "1", "2");

        toggleAll();
        assertTasks("1", "2");
        assertItemLeft(2);
    }

    @Test
    public void testEditCancelledAtAll() {
        givenAtAll(aTask("1", ACTIVE));

        startEdit("1", "1 edited cancelled").pressEscape();
        assertTasks("1");
        assertItemLeft(1);
    }

    @Test
    public void testClearCompletedAtAll() {
        givenAtAll(COMPLETED, "1", "2");

        clearCompleted();
        assertNoTasks();
    }

    @Test
    public void testEditByClickOutsideAtAll() {
        givenAtAll(aTask("1", ACTIVE));

        startEdit("1", "1 edited");
        newTodo.click();
        assertVisibleTasks("1 edited");
        assertItemLeft(1);
    }

    @Test
    public void testEditByClickTabAtAll() {
        givenAtAll(aTask("1", ACTIVE));

        startEdit("1", "1 edited").pressTab();
        assertVisibleTasks("1 edited");
        assertItemLeft(1);
    }

    @Test
    public void testDeleteByEmptyingEditedTextAtAll() {
        givenAtAll(aTask("1", ACTIVE));

        startEdit("1", "").pressEnter();
        assertNoTasks();
    }

    // *** Changing filter

    @Test
    public void goAtCompleted() {
        givenAtAll(aTask("1", COMPLETED));

        filterCompleted();
        assertVisibleTasks("1");
        assertItemLeft(0);
    }
}
