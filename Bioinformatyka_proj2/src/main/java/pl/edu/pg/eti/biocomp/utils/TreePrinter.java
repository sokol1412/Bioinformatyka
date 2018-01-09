package pl.edu.pg.eti.biocomp.utils;

import pl.edu.pg.eti.biocomp.models.Node;

import java.util.List;

public class TreePrinter {


    public static void print(String header, Node rootNode) {
        System.out.println(header);
        StringBuilder sb = new StringBuilder();
        sb = renderNode(rootNode, 0, sb, false);
    }

    private static StringBuilder renderNode(Node node, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append("[").append(node.printableLable()).append("]\n");
        List<Node> list = node.getChildren();
        for (int i = 0; i < list.size(); i++) {
            boolean last = ((i + 1) == list.size());
            if (list.get(i).getChildren().size() > 0) {
                renderNode(list.get(i), level + 1, sb, last);
            } else {
                renderLeaf(list.get(i), level + 1, sb, last);
            }
        }
        System.out.println(sb + "\n\n");
        return sb;
    }

    private static void renderLeaf(Node node, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append("--- [").append(node.printableLable()).append("]\n");
    }

    private static StringBuilder indent(StringBuilder sb, int level, boolean isLast) {
        for (int i = 1; i < level; i++) {
            sb.append("|  ");
        }
        if (level > 0) {
            sb.append(isLast ? "`-" : "|-");
        }
        return sb;
    }
}
