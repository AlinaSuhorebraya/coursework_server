package sample.dao.alternatives;

import sample.Organization.AlternativesForMarks;

import java.util.ArrayList;

public interface AlternativesDAO {
    void insertAlternative(AlternativesForMarks alternative);
    void deleteAlternative(int id);
    void update(AlternativesForMarks alternative);
    ArrayList<AlternativesForMarks> getAlternativeList();
}
