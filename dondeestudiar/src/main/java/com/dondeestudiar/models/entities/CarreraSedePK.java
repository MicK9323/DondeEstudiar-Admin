package com.dondeestudiar.models.entities;

import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CarreraSedePK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_carrera")
    private int idCarrera;

    @Column(name = "id_sede")
    private int idSede;

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idCarrera;
        result = prime * result + idSede;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        CarreraSedePK other = (CarreraSedePK) obj;
        if(idCarrera != other.idCarrera)
            return false;
        if(idSede != other.idSede)
            return false;
        return true;
    }
}
