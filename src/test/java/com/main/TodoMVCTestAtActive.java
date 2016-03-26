package com.main;

import org.junit.Test;

import static com.main.page.TodoMVCPage.*;
import static com.main.page.TodoMVCPage.TaskType.*;

/**
 * Created by stan on 25.03.16.
 */
public class TodoMVCTestAtActive {

    @Test
    public void testEditAtActive() {
        givenAtActive(aTask("1", ACTIVE));

        startEdit("1", "1 edited").pressEnter();
        assertTasks("1 edited");
        assertItemLeft(1);
    }

    @Test
    public void testDeleteAtActive() {
        givenAtActive(aTask("1", ACTIVE));

        delete("1");
        assertNoTasks();
    }

    @Test
    public void testCompleteAtActive() {
        givenAtActive(aTask("1", COMPLETED), aTask("2", ACTIVE));

        toggle("2");
        assertNoVisibleTasks();
        assertItemLeft(0);
    }

    @Test
    public void testReopenAllAtActive() {
        givenAtActive(aTask("1", ACTIVE), aTask("2", COMPLETED));

        toggleAll();
        assertNoVisibleTasks();
        assertItemLeft(0);
    }

    @Test
    public void testEditCancelledAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        startEdit("1", "1 edited cancelled").pressEscape();
        assertTasks("1", "2");
        assertItemLeft(2);
    }

    @Test
    public void testClearCompletedAtActive() {
        givenAtActive(aTask("1", ACTIVE), aTask("2", COMPLETED));

        clearCompleted();
        assertVisibleTasks("1");
        assertItemLeft(1);
    }

    @Test
    public void testEditByClickOutsideAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        startEdit("1", "1 edited");
        newTodo.click();
        assertTasks("1 edited", "2");
        assertItemLeft(2);
    }

    @Test
    public void testEditByClickTabAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        startEdit("1", "1 edited").pressTab();
        assertVisibleTasks("1 edited", "2");
        assertItemLeft(2);
    }

    @Test
    public void testDeleteByEmptyingEditedTextAtActive() {
        givenAtActive(ACTIVE, "1", "2");

        startEdit("1", "").pressEnter();
        assertVisibleTasks("2");
        assertItemLeft(1);
    }

    // *** Changing filter

    @Test
    public void goToAll() {
        givenAtActive(aTask("1", COMPLETED));

        filterAll();
        assertTasks("1");
        assertItemLeft(0);
    }
}
