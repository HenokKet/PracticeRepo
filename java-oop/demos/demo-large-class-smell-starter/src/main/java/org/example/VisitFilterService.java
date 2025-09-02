package org.example;

public class VisitFilterService {

    public Visit[] getVisitsByProviderId(String providerId, Visit[] visits) {
        Visit[] collector = new Visit[visits.length];
        int nextCollectorIndex = 0;

        for (Visit pv : visits) {
            if (pv.getProvider().getProviderId().equals(providerId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        Visit[] results = new Visit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }

    public Visit[] getVisitsByPatientId(String patientId, Visit[] visits) {
        Visit[] collector = new Visit[visits.length];
        int nextCollectorIndex = 0;

        for (Visit pv : visits) {
            if (pv.getPatient().getPatientId().equals(patientId)) {
                collector[nextCollectorIndex] = pv;
                nextCollectorIndex++;
            }
        }

        Visit[] results = new Visit[nextCollectorIndex];
        for (int i = 0; i < nextCollectorIndex; i++) {
            results[i] = collector[i];
        }
        return results;
    }
}
