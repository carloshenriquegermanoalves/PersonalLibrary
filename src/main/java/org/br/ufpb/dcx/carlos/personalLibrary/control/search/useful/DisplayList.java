package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayList {

    public void displayList(String title, List<Book> bookList) {
        if (!bookList.isEmpty()) {
            DefaultListModel<String> listModel = new DefaultListModel<>();

            for (Book book : bookList) {
                listModel.addElement(book.getTitle());
            }

            JList<String> resultList = new JList<>(listModel);
            resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(resultList);

            JDialog resultDialog = new JDialog();
            resultDialog.setTitle(title);
            getPanel(scrollPane, resultDialog);
        } else {
            JOptionPane.showMessageDialog(null,"Não foram encontrados livros por esse tipo de busca.");
        }
    }

    public static void getPanel(JScrollPane scrollPane, JDialog resultDialog) {
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        resultDialog.add(panel);
        resultDialog.setPreferredSize(new Dimension(400, 400));
        resultDialog.pack();
        resultDialog.setLocationRelativeTo(null);
        resultDialog.setVisible(true);
    }

}
