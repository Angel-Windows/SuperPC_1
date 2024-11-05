// Класс WN
package main;
import eduni.simjava.*;

public class WN extends Sim_entity {
    private Sim_port in, out; // добавляем выходной порт

    WN(String name, double delay) {
        super(name);
        in = new Sim_port("In");
        out = new Sim_port("Out"); // инициализация выходного порта
        add_port(in);
        add_port(out);
        System.out.println("WN created: " + name);
    }

    @Override
    public void body() {
//        System.out.println("WN started...");
        while (true) {
            Sim_event event = new Sim_event();
            sim_wait(event);
            if (event.from_port(in)) {
                System.out.println("WN processing event...");
                // Логика обработки
                sim_schedule(out, 0.0, 0); // отправка результата на выходной порт
                sim_completed(event);
                System.out.println("WN finished processing.");
            }
        }
    }
}
