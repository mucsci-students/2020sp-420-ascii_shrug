package shrugUML;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.util.SupplierUtil;

public class SimpleDirectedGraphDefault extends SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> {
  public SimpleDirectedGraphDefault() {
    super(() -> new ShrugUMLClass(), SupplierUtil.DEFAULT_EDGE_SUPPLIER, false);
  }

  public addEdge(DefaultEdge e) {
    addEdge(e.getSource(), e.getTarget());
  }
}
