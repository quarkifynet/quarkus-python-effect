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
    public static Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();
    private static boolean effectPyLoaded = false;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String input) throws IOException {
        if(!effectPyLoaded) {
            final URL effectPy = getClass().getClassLoader().getResource("effect.py");
            context.eval(Source.newBuilder("python", effectPy).build());
            System.out.println(effectPy);
        }
        final String pyCode = String.format("x = [%s]; y = [i*2 for i in x]; x.append(10)", input);
        context.eval("python", pyCode);
        Value effectValue = context.eval("python", "cohen_d(x,y)");
        return effectValue.toString();
    }
}
