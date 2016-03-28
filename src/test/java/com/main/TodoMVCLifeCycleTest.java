package com.main;

import org.junit.Test;
import static pages.TodoMVC.*;

/**
 * Created by stan on 21.03.16.
 */
public class TodoMVCLifeCycleTest {

    @Test
    public void testTaskLifeCycle() {
        givenAtAll();
        add("1");
        startEdit("1", "1 edited").pressEnter();
        assertTasks("1 edited");

        filterActive();
        assertTasks("1 edited");
        toggleAll();
        add("2");
        assertVisibleTasks("2");

        filterCompleted();
        assertVisibleTasks("1 edited");
        clearCompleted();
        assertNoVisibleTasks();

        filterAll();
        assertItemLeft(1);
        assertTasks("2");

        delete("2");
        assertNoTasks();

    }

}
