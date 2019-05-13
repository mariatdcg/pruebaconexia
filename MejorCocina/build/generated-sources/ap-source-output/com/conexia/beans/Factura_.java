package com.conexia.beans;

import com.conexia.beans.Camarero;
import com.conexia.beans.Cliente;
import com.conexia.beans.DetalleFactura;
import com.conexia.beans.Mesa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T23:37:50")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Cliente> cliente;
    public static volatile ListAttribute<Factura, DetalleFactura> detalleFactura;
    public static volatile SingularAttribute<Factura, Mesa> mesa;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile SingularAttribute<Factura, Date> fechaFactura;
    public static volatile SingularAttribute<Factura, Camarero> camarero;

}