package main;
import eduni.simjava.*;

public class CE extends Sim_entity {
    private Sim_port in1, in4, in5, in6;
    private Sim_port out1, out2, out3, outResult;
    private double delay;
    private int receivedParts = 0;

    CE(String name, double delay) {
        super(name);
        this.delay = delay;

        in1 = new Sim_port("In1");
        in4 = new Sim_port("In4");
        in5 = new Sim_port("In5");
        in6 = new Sim_port("In6");

        // Output ports
        out1 = new Sim_port("Out1");
        out2 = new Sim_port("Out2");
        out3 = new Sim_port("Out3");
        outResult = new Sim_port("OutResult"); // To Client

        add_port(in1); add_port(in4); add_port(in5); add_port(in6);
        add_port(out1); add_port(out2); add_port(out3);
        add_port(outResult);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event event = new Sim_event();
            sim_wait(event);

            // Receive task from Client
            if (event.from_port(in1)) {
                sim_process(delay);
                sim_completed(event);

                // Split task and send to 3 nodes
                sim_trace(Sim_system.get_trc_level(), "CE has split the task into 3 parts.");
                sim_schedule(out1, 0.0, 0);
                sim_schedule(out2, 0.0, 0);
                sim_schedule(out3, 0.0, 0);
            }

            // Collect partial results
            if (event.from_port(in4) || event.from_port(in5) || event.from_port(in6)) {
                receivedParts++;
                sim_trace(Sim_system.get_trc_level(), "CE received a partial result.");

                // If all parts are received, send the final result to Client
                if (receivedParts == 3) {
                    sim_trace(Sim_system.get_trc_level(), "CE has combined all results and sent to Client.");
                    sim_schedule(outResult, 0.0, 0);
                    receivedParts = 0;
                }
            }
        }
    }
}
