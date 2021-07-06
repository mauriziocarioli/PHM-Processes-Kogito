package com.health_insurance.phm_processes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import com.health_insurance.phm_model.*;

import java.util.*;

/**
 * WorkItemHandler for assigning actor contact information.
 *
 * Expects the following parameter:
 *  - "actionList" (java.util.List): the list of actions
 * It returns the parameter:
 *  - "Return" (java.util.List<Action>): the list of actions
 */

public class GetContactsWorkItemHandler  implements WorkItemHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetContactsWorkItemHandler.class);

    public GetContactsWorkItemHandler() {
        logger.info("GetContactWorkItemHandler has been registered.");
    }

    public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
        logger.info("GetContactsWorkItemHandler execution has started.");
        ArrayList<Action> l = (ArrayList<Action>)wi.getParameter("actionList");
        HashMap<String, Object> p = new HashMap<String, Object>();
        for(Action a: l) {
            a.setDescription(a.getDescription().replaceFirst("_Member_","Mary"));    
            a.setMessage(a.getMessage().replaceFirst("_Member_","Mary"));    
            a.setReminderFromAddress("phm@myhealthinsurance.com");
            a.setReminderSubject(a.getReminderSubject().replaceFirst("_Member_","Mary"));
            switch (a.getActor()) {
                case "_Provider_":
                    a.setActor("Peter");
                    a.setReminderSendAddress("peter@doctors.com");
                    a.setActorPhoneNumber("123-456-7890");
                    a.setEscalationActor("Charlie");
                   a.setReminderText(a.getReminderText().replaceFirst("_Actor_","Peter"));
                    break;
                case "_Case Nurse_":
                    a.setActor("Charlie");
                    a.setReminderSendAddress("charlie@myhealthinsurance.com");
                    a.setActorPhoneNumber("123-456-7890");
                    a.setEscalationActor("Marc");
                    a.setReminderText(a.getReminderText().replaceFirst("_Actor_","Charlie"));
                    break;
                case "_Rx_":
                    a.setActor("Robert");
                    a.setReminderSendAddress("bob@rx.com");
                    a.setActorPhoneNumber("123-456-7890");
                    a.setEscalationActor("Charlie");
                    a.setReminderText(a.getReminderText().replaceFirst("_Actor_","Robert"));
                    break;
                case "_Member_":
                    a.setActor("Mary");
                    a.setReminderSendAddress("mary@mail.com");
                    a.setActorPhoneNumber("123-456-7890");
                    a.setEscalationActor("Charlie");
                    a.setReminderText(a.getReminderText().replaceFirst("_Actor_","Mary"));
                    break;
            }
        }
        p.put("Result",l);
        wim.completeWorkItem(wi.getId(),p);
        logger.info("GetContactsWorkItemHandler execution has completed.");
    }

    public void abortWorkItem(WorkItem wi, WorkItemManager wim) {
        logger.info("GetContactsWorkItemHandler execution has been aborted.");
    }

}
