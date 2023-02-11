package springboot.hello;

public class SimpleHelloService implements HelloService {
    @Override
    public String sayService(String name) {
        return name + " service complete";
    }
}
