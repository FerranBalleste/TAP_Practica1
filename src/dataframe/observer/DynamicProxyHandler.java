package dataframe.observer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicPtoxyHandler {
    //Declare Proxy Object
    private Object target;

    //Create Interceptor
    InterceptorClass interceptor = new InterceptorClass();

    //Dynamically generates a proxy object and binds the proxy class and proxy processor
    public Object getProxyInstance(final Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        interceptor.before();
                        Object result = method.invoke(target, args);
                        interceptor.after();
                        return result;
                    }
                });
    }
}
