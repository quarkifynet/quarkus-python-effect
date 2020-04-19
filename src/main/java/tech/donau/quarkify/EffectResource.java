package tech.donau.quarkify;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/effect")
public class EffectResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws IOException {
        String effectPy = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("effect.py").getPath())), StandardCharsets.UTF_8);
        System.out.println(effectPy);
        Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();
	System.out.println("before");
        Value effectValue = context.eval("python", effectPy);
	System.out.println("aftter");
        return effectValue.toString();
    }
}
