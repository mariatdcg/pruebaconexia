package com.conexia.beans;

import com.conexia.beans.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T23:37:50")
@StaticMetamodel(Mesa.class)
public class Mesa_ { 

    public static volatile SingularAttribute<Mesa, Integer> numMaxComensales;
    public static volatile SingularAttribute<Mesa, String> ubicacion;
    public static volatile SingularAttribute<Mesa, Integer> idMesa;
    public static volatile ListAttribute<Mesa, Factura> facturaList;

}