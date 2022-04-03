package ev.laborai.pirmaslab.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LoggedInvocation
public class MethodLogger {

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        System.out.println("Method: " + context.getMethod().getName() + " invoked.");
        return context.proceed();
    }

}
