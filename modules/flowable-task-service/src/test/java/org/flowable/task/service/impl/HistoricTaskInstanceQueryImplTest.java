package org.flowable.task.service.impl;

import org.flowable.common.engine.api.scope.ScopeTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class HistoricTaskInstanceQueryImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testProcessInstanceId_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.processInstanceId("456");

        // Assert
        assertSame(query, result); // Expecting the same query object to be returned
        assertEquals("456", query.processInstanceId); // Expecting processInstanceId to be set
        assertNull(query.currentOrQueryObject); // Expecting currentOrQueryObject to be null
    }


    @Test
    public void testExecutionId_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.executionId("456");

        // Assert
        assertSame(query, result); // Expecting the same query object to be returned
        assertEquals("456", query.executionId); // Expecting executionId to be set
    }

    @Test
    public void testCaseInstanceId_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseInstanceId("456");

        // Assert
        assertSame(query, result); // Expecting the same query object to be returned
        assertEquals("456", query.scopeId); // Expecting scopeId to be set
        assertEquals(ScopeTypes.CMMN, query.scopeType); // Expecting scopeType to be set
    }

    @Test
    public void testCaseDefinitionKeyLike_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseDefinitionKeyLike("def");

        // Assert
        assertSame(query, result); // Expecting the same query object to be returned
        assertEquals("def", query.caseDefinitionKeyLike); // Expecting caseDefinitionKeyLike to be set
    }

    @Test
    public void testCaseDefinitionKeyLikeIgnoreCase_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseDefinitionKeyLikeIgnoreCase("def");

        // Assert
        assertSame(query, result); // Expecting the same query object to be returned
        assertEquals("def", query.caseDefinitionKeyLikeIgnoreCase); // Expecting caseDefinitionKeyLikeIgnoreCase to be set
    }

    @Test
    public void testCaseInstanceId_whenNotInOrStatement2() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseInstanceId("testId");

        // Assert
        assertSame(query, result);
        assertEquals("testId", query.scopeId);
        assertEquals(ScopeTypes.CMMN, query.scopeType);
    }

    @Test
    public void testCaseDefinitionId_whenNotInOrStatement() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseDefinitionId("testDefId");

        // Assert
        assertSame(query, result);
        assertEquals("testDefId", query.scopeDefinitionId);
        assertEquals(ScopeTypes.CMMN, query.scopeType);
    }

    @Test
    public void testCaseDefinitionKeyLike_whenNotInOrStatement2() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseDefinitionKeyLike("testKey");

        // Assert
        assertSame(query, result);
        assertEquals("testKey", query.caseDefinitionKeyLike);
    }

    @Test
    public void testCaseDefinitionKeyLikeIgnoreCase_whenNotInOrStatement2() {
        // Arrange
        HistoricTaskInstanceQueryImpl query = new HistoricTaskInstanceQueryImpl();
        query.inOrStatement = false;

        // Act
        HistoricTaskInstanceQueryImpl result = query.caseDefinitionKeyLikeIgnoreCase("testKeyIgnoreCase");

        // Assert
        assertSame(query, result);
        assertEquals("testKeyIgnoreCase", query.caseDefinitionKeyLikeIgnoreCase);
    }
}