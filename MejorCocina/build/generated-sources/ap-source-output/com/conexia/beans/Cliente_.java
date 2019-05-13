package com.conexia.beans;

import com.conexia.beans.Factura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T23:37:50")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellido2;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile SingularAttribute<Cliente, String> apellido1;
    public static volatile ListAttribute<Cliente, Factura> facturas;
    public static volatile SingularAttribute<Cliente, String> observaciones;
    public static volatile SingularAttribute<Cliente, String> nombre;

}