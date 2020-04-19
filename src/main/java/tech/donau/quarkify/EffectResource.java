package tech.donau.quarkify;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

@Path("/effect")
public class EffectResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String input) throws IOException {
        final URL effectPy = getClass().getClassLoader().getResource("effect.py");
        System.out.println(effectPy);
        Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();
        context.eval("python", String.format("x = [%s]", input));
        Value effectValue = context.eval(Source.newBuilder("python", effectPy).build());
        return effectValue.toString();
    }
}
