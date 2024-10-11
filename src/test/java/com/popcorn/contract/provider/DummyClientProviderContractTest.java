package com.popcorn.contract.provider;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.spring6.PactVerificationSpring6Provider;
import au.com.dius.pact.provider.spring.spring6.Spring6MockMvcTestTarget;
import com.popcorn.controller.DummyController;
import com.popcorn.model.ComplexResponseObject;
import com.popcorn.service.DummyService;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@ExtendWith({SpringExtension.class, PactVerificationSpring6Provider.class})
@WebMvcTest(controllers = {DummyController.class})
@Provider(value = DummyClientProviderContractTest.PROVIDER_NAME)
@PactFolder(value = "pacts")
@IgnoreNoPactsToVerify
@ActiveProfiles(profiles = {"contract-test"})
@Slf4j
public class DummyClientProviderContractTest {
    public static final String PROVIDER_NAME = "dummy-client-provider";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DummyService dummyService;

    @TestTemplate
    @ExtendWith(PactVerificationSpring6Provider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        }
    }

    @BeforeEach
    void beforeEach(PactVerificationContext context) {
        Spring6MockMvcTestTarget testTarget = new Spring6MockMvcTestTarget();
        testTarget.setControllers(new DummyController(dummyService));

        context.setTarget(testTarget);
        context.setTarget(new Spring6MockMvcTestTarget(mockMvc));
    }

    @State(value = "update data using complex request object")
    void updateDataUsingComplexRequestObject() {
        ComplexResponseObject mockedResponse = ComplexResponseObject.builder()
                .age((byte) 25)
                .salary((short) 5000)
                .married(true)
                .gender('M')
                .count(100)
                .height(5.9f)
                .serialNumber(123456789L)
                .amount(2500.75)
                .name("John Doe")
                .uuid(UUID.fromString("fb427153-cdb0-471f-aa39-59f0fb458d45"))
                .revenue(new BigDecimal("100000.50"))
                .additionalProperties(Map.of())
                .build();
        Mockito.when(dummyService.update(Mockito.any()))
                .thenReturn(mockedResponse);
    }
}
