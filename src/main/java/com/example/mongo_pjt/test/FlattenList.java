package com.example.mongo_pjt.test;

import lombok.extern.log4j.Log4j2;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;

@Log4j2
public class FlattenList {

    List<List<String>> nestedList = asList(
            asList("one:one"),
            asList("two:one", "two:two", "two:three"),
            asList("three:one", "three:two", "three:three", "three:four"));

    // data form -> { { "one:one" }, { "two:one", "two:two","two:three" }, { "three:one", "three:two", "three:three", "three:four" } }

    //     private static final StopWatch stopWatch = new StopWatch();

    public <T> List<T> flattenListOfListsImperatively(List<List<T>> nestedList) {
        List<T> ls = new ArrayList<>();
        log.info("nestedList size before flattened : " + nestedList);
        nestedList.forEach(ls::addAll);
        return ls;
    }

    @Test
    public void givenNestedList_thenFlattenImperatively() {
        List<String> ls = flattenListOfListsImperatively(nestedList);
        log.info("nested List size after flattened : " + ls);

        assertNotNull(ls);
        assertTrue(ls.size() == 8);
    }

    public <T> List<T> flattenListOfListsStream(List<List<T>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Test
    public void givenNestedList_thenFlattenFunctionally() {
        List<String> ls = flattenListOfListsStream(nestedList);

        assertNotNull(ls);
        assertTrue(ls.size() == 8);
    }
}
