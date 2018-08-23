Feature: save_competence_candidat
  Your are a candidate and want to specify a skill you've acquired in a domain
  with a level you've estimated.

  Scenario:
    Given an authenticated candidate opening appropriate modal to add a comeptence
    When selecting a secteur
    Then getting related competences
    When selecting a competence, a level and submitting
    Then candidat's competence is successfully saved