package tech.donau.quarkify;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

@Path("/effect")
public class EffectResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws IOException {
        final URL effectPy = getClass().getClassLoader().getResource("effect.py");
        System.out.println(effectPy);
        Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();
        Value effectValue = context.eval(Source.newBuilder("python", effectPy).build());
        return effectValue.toString();
    }
}
