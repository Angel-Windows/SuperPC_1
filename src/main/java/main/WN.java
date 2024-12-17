package main;
import eduni.simjava.*;

public class WN extends Sim_entity {
    private Sim_port in, out;
    private double delay;

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
                // Process the task part
                sim_process(delay);
                sim_completed(event);

                // Log and send the result back to CE
                sim_trace(Sim_system.get_trc_level(), get_name() + " is processing its part with delay: " + delay);
                sim_schedule(out, 0.0, 0);
            }
        }
    }
}
