package main;
import eduni.simjava.*;

public class Grid {
    public static void main(String[] args) {
        Sim_system.initialise();

        Client client = new Client("Client", 1.0);
        CE ce = new CE("CE", 2.0);

        // Create 3 nodes with different delays (productivity)
        WN wn1 = new WN("WN_1", 10.0); // Fastest
        WN wn2 = new WN("WN_2", 20.0); // Medium
        WN wn3 = new WN("WN_3", 30.0); // Slowest

        // Link ports between entities
        Sim_system.link_ports("Client", "Out", "CE", "In1");
        Sim_system.link_ports("CE", "Out1", "WN_1", "In");
        Sim_system.link_ports("CE", "Out2", "WN_2", "In");
        Sim_system.link_ports("CE", "Out3", "WN_3", "In");
        Sim_system.link_ports("WN_1", "Out", "CE", "In4");
        Sim_system.link_ports("WN_2", "Out", "CE", "In5");
        Sim_system.link_ports("WN_3", "Out", "CE", "In6");
        Sim_system.link_ports("CE", "OutResult", "Client", "In");

        Sim_system.set_trace_detail(true, true, true);
        Sim_system.run();
    }
}
