package main.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import main.block.DraggableRect;

public class Run {
	// generates .java file
	public static void genJava(DraggableRect r) {
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(Save.getPath() + Save.getFile() + ".java"), "utf-8"))) {
			writer.write(genCode(r));
		} catch (IOException ex) {
			// report
		}
	}

	public static String genCode(DraggableRect r) {
		if (r != null) {
			if (r.hasChildren()) {
				switch (r.getType()) {
				case 0:
					return genCode(Controller.getRectByID(r.childrenIDs.get(0)));
				case 1:
					// TODO update assignment rect
					return r.f1 + " = " + r.f2 + ";\n" + genCode(Controller.getRectByID(r.childrenIDs.get(0)));
				case 2:
					return r.f1 + " " + r.f3 + " " + r.f2;
				case 3:
					return "if(" + /*
									 * genCode(Controller.getRectByID(r.
									 * childrenIDs. get(0))) +
									 */"){\n\t" + genCode(Controller.getRectByID(r.childrenIDs.get(0))) + "\n\b} else {\n"
							+ genCode(Controller.getRectByID(r.childrenIDs.get(1))) + "\n}";
				case 4:
					return "while(" + r.f1 + "){\n\t" + genCode(Controller.getRectByID(r.childrenIDs.get(0))) + "\n\b}"
							+ genCode(Controller.getRectByID(r.childrenIDs.get(1)));
				case 5:
					return "public static void main(String[] args){\n\t"
							+ genCode(Controller.getRectByID(r.childrenIDs.get(0))) + "\n\b}";
				case 6:
					String message = "switch(" + r.f1 + ":\n\t";
					for (int i = 0; i < r.childrenIDs.size(); i++) {
						if (Controller.getRectByID(r.childrenIDs.get(i)) != null) {
							message += "case " + i + ":\n\t";
							message += genCode(Controller.getRectByID(r.childrenIDs.get(i))) + "\n\b";
						} else {
							return message + "\n\b}";
						}
					}
					break;
				case 7:
					return r.f1 + "{\n\t" + genCode(Controller.getRectByID(r.childrenIDs.get(0))) + "\n\b}";
				case 8:
					return r.f1 + genCode(Controller.getRectByID(r.childrenIDs.get(0)));
				default:
					return Integer.toString(r.getType());
				}
			} else {
				switch (r.getType()) {
				case 0:
					return "";
				case 1:
					return "[TYPE] " + r.f1 + " = " + r.f2;
				case 2:
					return r.f1 + " " + r.f3 + " " + r.f2;
				case 3:
					return "if(" + r.f1 + "){}";
				case 4:
					return "while(" + r.f1 + "){}";
				case 5:
					return "public static void main(String[] args){}";
				case 6:
					return "switch(" + r.f1 + "){}";
				case 7:
					return r.f1 + "{}";
				case 8:
					return r.f1;
				default:
					return Integer.toString(r.getType());
				}
			}
		}
		return "";
	}
}
