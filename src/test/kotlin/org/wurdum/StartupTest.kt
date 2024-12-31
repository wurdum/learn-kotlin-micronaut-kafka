package org.wurdum

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.wurdum.infra.AbstractIntegrationTest

class StartupTest : AbstractIntegrationTest() {

    @Test
    fun application_always_starts() {
        Assertions.assertTrue(server.isRunning)
    }
}
