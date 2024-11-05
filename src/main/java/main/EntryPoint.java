package main;
import eduni.simjava.*;

public class EntryPoint {
    public static void main(String[] args) {
        Sim_system.initialise();

        Client client = new Client("Client", 1.0);
        CE ce = new CE("CE", 2.0);
        WN wn = new WN("WN", 3.0);

        // Подключение портов
        Sim_system.link_ports("Client", "Out", "CE", "In1");
        Sim_system.link_ports("CE", "Out1", "WN", "In");
        Sim_system.link_ports("WN", "Out", "CE", "In2"); // Подключение выходного порта WN
        Sim_system.link_ports("CE", "Out2", "Client", "In");

        Sim_system.set_trace_detail(false, true, false);
        Sim_system.run();
    }
}
