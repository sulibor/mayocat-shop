package org.mayocat.configuration.jackson;

import java.io.IOException;

import org.mayocat.configuration.Configurable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

/**
 * @version $Id$
 */
public class GestaltConfigurationModule extends Module
{
    private static class ConfigurableSerializer extends JsonSerializer<Configurable>
    {
        @Override
        public void serialize(Configurable value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException
        {
            provider.defaultSerializeValue(value.getValue(), jgen);
        }
    }

    @Override
    public String getModuleName()
    {
        return "gestaltConfiguration";
    }

    @Override
    public Version version()
    {
        return Version.unknownVersion();
    }

    @Override
    public void setupModule(SetupContext context)
    {
        SimpleSerializers serializers = new SimpleSerializers();
        serializers.addSerializer(Configurable.class, new ConfigurableSerializer());
        context.addSerializers(serializers);
    }
}
