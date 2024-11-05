package main;
import eduni.simjava.*;

public class WN extends Sim_entity {
    private Sim_port in, out;
    private double delay;
    public static Boolean flag = false;

    WN(String name, double delay) {
        super(name);
        this.delay = delay;
        in = new Sim_port("In");
        out = new Sim_port("Out");
        add_port(in);
        add_port(out);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event event = new Sim_event();
            sim_get_next(event);
            if (event.from_port(in)) {
                sim_process(delay);
                sim_completed(event);

                // Вывод с указанием имени узла
                sim_trace(Sim_system.get_trc_level(), get_name() + " has processed the task.");

                // Отправляем результат обратно в CE
                sim_schedule(out, delay, 0);
            }
        }
    }
}
