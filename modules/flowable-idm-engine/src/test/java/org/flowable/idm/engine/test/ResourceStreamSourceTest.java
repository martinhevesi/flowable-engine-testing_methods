package org.flowable.idm.engine.test;

import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.impl.util.io.StreamSource;
import org.flowable.idm.engine.impl.io.ResourceStreamSource;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResourceStreamSourceTest extends PluggableFlowableIdmTestCase {

    @Test
    public void createResourceStream() {
        String nonExistentResource = "nothing";
        StreamSource streamSource = new ResourceStreamSource(nonExistentResource);
        assertThatThrownBy(() -> {
            InputStream inputStream = streamSource.getInputStream();
        })
                .isInstanceOf(FlowableException.class)
                .hasMessage("resource 'nothing' doesn't exist");
        assertThat(streamSource).hasToString("Resource[nothing]");
    }
}
