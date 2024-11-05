package main;
import eduni.simjava.*;

public class CE extends Sim_entity {
    private Sim_port in1, out1, in2, out2;

    public static boolean link = true; // Используемый для логики

    CE(String name, double delay) {
        super(name);
        in1 = new Sim_port("In1");
        out1 = new Sim_port("Out1");
        in2 = new Sim_port("In2");
        out2 = new Sim_port("Out2");
        add_port(in1);
        add_port(out1);
        add_port(in2);
        add_port(out2);
        System.out.println("CE created: " + name);
    }

    @Override
    public void body() {
        System.out.println("CE started...");
        // Ваша логика обработки
        while (true) {
            Sim_event event = new Sim_event();
            sim_wait(event);
            // Обработка событий
            if (event.from_port(in1)) {
                System.out.println("CE processing event from In1");
                sim_schedule(out1, 0.0, 0); // Отправляем результат
            } else if (event.from_port(in2)) {
                System.out.println("CE processing event from In2");
                sim_schedule(out2, 0.0, 0); // Отправляем результат
            }
        }
    }
}