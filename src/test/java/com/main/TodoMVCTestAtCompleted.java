package com.main;

import org.junit.Test;
import static com.main.page.TodoMVCPage.TaskType.*;
import static com.main.page.TodoMVCPage.*;

/**
 * Created by stan on 25.03.16.
 */
public class TodoMVCTestAtCompleted {

    @Test
    public void testAddAtCompleted() {
        givenAtCompleted(aTask("1", ACTIVE));
        add("2");

        assertNoVisibleTasks();
        assertItemLeft(2);
    }

    @Test
    public void testEditAtCompleted() {
        givenAtCompleted(COMPLETED, "1", "2");

        startEdit("2", "2 edited").pressEnter();
        assertVisibleTasks("1", "2 edited");
        assertItemLeft(0);
    }

    @Test
    public void testCompleteAllAtCompleted() {
        givenAtCompleted(ACTIVE, "1", "2");

        toggleAll();
        assertVisibleTasks("1", "2");
        assertItemLeft(0);
    }

    @Test
    public void testReopenAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED));

        toggle("1");
        assertNoVisibleTasks();
        assertItemLeft(1);
    }

    @Test
    public void testReopenAllAtCompleted() {
        givenAtCompleted(aTask("1", ACTIVE), aTask("2", ACTIVE), aTask("3", COMPLETED));

        toggleAll();
        assertVisibleTasks("1", "2", "3");
        assertItemLeft(0);
    }

    @Test
    public void testEditCancelledAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED));

        startEdit("1", "1 edited cancelled").pressEscape();
        assertTasks("1");
        assertItemLeft(0);
    }

    @Test
    public void testEditByClickOutsideAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED));

        startEdit("1", "1 edited");
        newTodo.click();
        assertTasks("1 edited");
        assertItemLeft(0);
    }

    @Test
    public void testEditByClickTabAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED));

        startEdit("1", "1 edited").pressTab();
        assertTasks("1 edited");
        assertItemLeft(0);
    }

    @Test
    public void testDeleteByEmptyingEditedTextAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED));

        startEdit("1", "").pressEnter();
        assertNoTasks();
    }

    @Test
    public void testDeleteAtCompleted() {
        givenAtCompleted(aTask("1", COMPLETED), aTask("2", ACTIVE));

        delete("1");
        assertNoVisibleTasks();
        assertItemLeft(1);
    }

    // *** Changing filter

    @Test
    public void goToActive() {
        givenAtCompleted(aTask("1", ACTIVE), aTask("2", COMPLETED));

        filterActive();
        assertVisibleTasks("1");
        assertItemLeft(1);
    }
}
