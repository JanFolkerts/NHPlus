package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Treatment {
    private long tid;
    private final long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private final long cid;
    private String caregiverSurname;
    private String caregiverFirstname;
    private String caregiverPhonenumber;


    /**
     * Constructor to initiate an object of class <code>Treatment</code> with the given parameter. Use this constructor
     * to initiate objects, which are not persisted yet, because it will not have a treatment id (tid).
     *
     * @param pid         Pid of the treated patient.
     * @param date        Date of the Treatment.
     * @param begin       Time of the start of the treatment in format "hh:MM"
     * @param end         Time of the end of the treatment in format "hh:MM".
     * @param description Description of the treatment.
     * @param remarks     Remarks to the treatment.
     */

    public Treatment(long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, long cid) {
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.cid = cid;
    }

    /**
     * Constructor to initiate an object of class <code>Treatment</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a treatment id (tid).
     *
     * @param tid         id of the treatment.
     * @param pid         PId of the treated patient.
     * @param date        Date of the Treatment.
     * @param begin       Time of the start of the treatment in format "hh:MM"
     * @param end         Time of the end of the treatment in format "hh:MM".
     * @param description Description of the treatment.
     * @param remarks     Remarks to the treatment.
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks, long cid) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.cid = cid;
    }

    /**
     * Constructor to initialize an instance of the <code>Treatment</code> class with the specified parameters.
     * This constructor is used for objects that are already persisted and have a treatment ID (tid).
     *
     * @param tid                   Unique identifier of the treatment.
     * @param pid                   Unique identifier of the treated patient.
     * @param date                  Date of the treatment session.
     * @param begin                 Start time of the treatment (format: "hh:MM").
     * @param end                   End time of the treatment (format: "hh:MM").
     * @param description           Description detailing the treatment procedure.
     * @param remarks               Additional remarks regarding the treatment.
     * @param cid                   Unique identifier of the assigned caregiver.
     * @param caregiverSurname      Last name of the caregiver responsible for the treatment.
     * @param caregiverFirstname    First name of the caregiver.
     * @param caregiverPhonenumber  Contact number of the caregiver.
     */
    public Treatment(long tid, long pid, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks,
                     long cid, String caregiverSurname, String caregiverFirstname, String caregiverPhonenumber) {
        this.tid = tid;
        this.pid = pid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.cid = cid;
        this.caregiverSurname = caregiverSurname;
        this.caregiverFirstname = caregiverFirstname;
        this.caregiverPhonenumber = caregiverPhonenumber;
    }

    public long getTid() {
        return tid;
    }

    public long getPid() {
        return this.pid;
    }

    public String getDate() {
        return date.toString();
    }

    public String getBegin() {
        return begin.toString();
    }

    public String getEnd() {
        return end.toString();
    }

    public long getCaregiverId() {
        return cid;
    }


    public void setDate(String date) {
        this.date = DateConverter.convertStringToLocalDate(date);
    }

    public void setBegin(String begin) {
        this.begin = DateConverter.convertStringToLocalTime(begin);
    }

    public void setEnd(String end) {
        this.end = DateConverter.convertStringToLocalTime(end);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Retrieves the unique identifier of the caregiver associated with this treatment.
     *
     * @return The caregiver's ID (`cid`).
     */

    public long getCid() {
        return cid;
    }

    /**
     * Retrieves the surname of the caregiver associated with this treatment.
     *
     * @return The caregiver's last name.
     */

    public String getCaregiverSurname() {
        return caregiverSurname;
    }

    /**
     * Sets the surname of the caregiver associated with this treatment.
     *
     * @param caregiverSurname The last name of the caregiver.
     */

    public void setCaregiverSurname(String caregiverSurname) {
        this.caregiverSurname = caregiverSurname;
    }

    /**
     * Retrieves the first name of the caregiver associated with this treatment.
     *
     * @return The caregiver's first name.
     */

    public String getCaregiverFirstname() {
        return caregiverFirstname;
    }

    /**
     * Sets the first name of the caregiver associated with this treatment.
     *
     * @param caregiverFirstname The first name of the caregiver.
     */

    public void setCaregiverFirstname(String caregiverFirstname) {
        this.caregiverFirstname = caregiverFirstname;
    }

    /**
     * Retrieves the phone number of the caregiver associated with this treatment.
     *
     * @return The caregiver's phone number.
     */

    public String getCaregiverPhonenumber() {
        return caregiverPhonenumber;
    }

    /**
     * Sets the phone number of the caregiver associated with this treatment.
     *
     * @param caregiverPhonenumber The phone number of the caregiver.
     */

    public void setCaregiverPhonenumber(String caregiverPhonenumber) {
        this.caregiverPhonenumber = caregiverPhonenumber;
    }

    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks +
                "\nCID: " + this.cid +
                "\nCaregiver Surname: " + this.caregiverSurname +
                "\nCaregiver Firstname: " + this.caregiverFirstname +
                "\nCaregiver Phonenumber: " + this.caregiverPhonenumber +
                "\n";
    }
}
