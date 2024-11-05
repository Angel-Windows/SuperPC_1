package main;
import eduni.simjava.*;

public class EntryPoint {
    public static void main(String[] args) {
        Sim_system.initialise();
        Client client = new Client("Client", 1.0);
        CE ce = new CE("CE", 1.0);
        WN wn = new WN("WN", 1.0);

        Sim_system.link_ports("Client", "Out", "CE", "In1");
        Sim_system.link_ports("CE", "Out1", "WN", "In");
        Sim_system.link_ports("WN", "Out", "CE", "In2");

        Sim_system.set_trace_detail(false, true, false);
        Sim_system.run();
    }
}
