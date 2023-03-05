package springboot.hello;

import org.springframework.stereotype.Service;

@Service
public class SimpleHelloService implements HelloService {
    @Override
    public String sayService(String name) {
        return name + " service complete";
    }
}
