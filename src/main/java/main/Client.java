package main;
import eduni.simjava.*;

public class Client extends Sim_entity {
    private Sim_port in, out;

    Client(String name, double delay) {
        super(name);
        in = new Sim_port("In");
        out = new Sim_port("Out");
        add_port(in);
        add_port(out);
    }

    @Override
    public void body() {
        // Send a single task to CE
        sim_trace(Sim_system.get_trc_level(), "Client has sent a task.");
        sim_schedule(out, 0.0, 0);

        // Wait for the final result
        Sim_event event = new Sim_event();
        sim_wait(event);

        if (event.from_port(in)) {
            sim_trace(Sim_system.get_trc_level(), "Client received the final result. Task completed!");
            System.out.println("Task Completed!");
        }
    }
}
