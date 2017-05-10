/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator;

import entity.Report;
import java.util.Comparator;

/**
 *
 * @author Purnama
 */
public class ReportComparator implements Comparator<Report>{

    @Override
    public int compare(Report o1, Report o2) {
        return -o1.getDate().compareTo(o2.getDate());
    }
}