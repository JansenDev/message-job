package com.mrjoi.messagejob.utils;

import com.mrjoi.messagejob.model.BoletaEntrada;
import com.mrjoi.messagejob.model.Reserva;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class VoucherTemplate {


    public VoucherTemplate() {
    }

    public String getVoucherReservaTemplate(Reserva reserva) {

        String TEMPLATE = """
                <body>
                    <div style="width:600px; margin: auto; font-family:Arial, Helvetica, sans-serif">
                        <div >
                            <h2 style="text-align: center;">Boleta de venta electronica</h2>
                        </div>
                        <div  style="text-align: center;">
                            <img style="height: 78px; width: 176px;" src="https://iili.io/HMLqXJS.png" />
                            <p style="margin-top: 15px;">Ate 15494</p>
                            <p style="margin-top: -10px;">Local Puruchuco</p>
                        </div>
                        <hr style="min-width: 600px;height: 1px; margin: 1rem 0;color: inherit;background-color: currentColor;border: 0;" />
                        <div  style="padding-bottom: 1em; display: flex;">
                            <div style="padding-right: 80px;">
                                <h5 style="font-weight: 900;font-size: 0.8em;">Cliente</h5>
                                <p style="font-size: 15px;">${nombres} ${apellidos}</p>
                                <h5 style="font-weight: 900;font-size: 0.8em;">Fecha Reserva:</h5>
                                <p style="font-size: 15px;">${fecha_reserva} ${hora_reserva} am</p>
                            </div>
                            <div style="padding-right: 40px;">
                                <h5 style="font-weight: 900;;font-size: 0.8em;">N° de boleta:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha registro:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha vencimiento:</h5>
                            </div>
                            <div>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">${num_boleta}</h5>
                                <p>${fecha_registro}</p>
                                <p>${fecha_reserva}</p>
                            </div>
                        </div>
                        <div style="padding-bottom: 40px;display: grid;grid-template-rows: minmax(220px,auto);">
                            <table style="table-layout: fixed;border-collapse: collapse;">
                                <thead style="border-top: solid 3px #000; border-bottom: 3px solid #000; height: 65px;">
                                <tr>
                                    <th style="padding-top: 30px;">Cant. Personas</th>
                                    <th style="padding-top: 30px;">Descripcion</th>
                                    <th style="padding-top: 30px;">Precio Unitario</th>
                                    <th style="padding-top: 30px;">Importe</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr style="height:40px;">
                                    <td style="text-align: center">${cantidad_personas}</td>
                                    <td style="text-align: center">Reserva de cumpleaños</td>
                                    <td style="text-align: right">${personas_price_unit}</td>
                                    <td style="text-align: right">${personas_price_total}</td>
                                </tr>
                                <tr style="height:40px;">
                                    <td style="text-align: center">${cantidad_acompaniante}</td>
                                    <td style="text-align: center">Reserva de cumpleaños</td>
                                    <td style="text-align: right">${acompaniante_price_unit}</td>
                                    <td style="text-align: right">${acompaniante_price_total}</td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">V. venta</th>
                                        <th style="text-align: right">${valor_venta}</th>
                                    </tr>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">IGV 18%</th>
                                        <th style="text-align: right">${IGV_total}</th>
                                    </tr>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">Total </th>
                                        <th style="text-align: right">${tota_pago}</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div  style="border-top: solid 2px #000; padding-top: 20px;">
                            <div style="display:flex">
                                <div style="padding-right: 3em;">
                                    <h4>Condiciones y formas de pago</h4>
                                </div>
                                <div>
                                    <img style="height: 40px;width: 40px;" src="https://www.mrjoy.com.ec/wp-content/uploads/2022/03/favicon1.png"/>
                                </div>
                            </div>

                            <p></p>
                            <p>
                            Banco Banreserva
                            <br />
                            IBAN: DO XX 0075 XXXX XX XX XXXX XXXX
                            <br />
                            Código SWIFT: BPDODOSXXXX
                            </p>
                        </div>
                    </div>
                </body>""";

        double acompaniante_price_unit= 6.0;
        double acompaniante_price_total= reserva.getAcompaniante() * acompaniante_price_unit ;
        double personas_price_total = reserva.getTotalPago() - acompaniante_price_total;
        double personas_price_unit = personas_price_total / reserva.getCantPersonas();
        double IGV_perc = 0.18;
        double IGV_total = reserva.getTotalPago() * 0.18;
        double IGV_total_round = Math.round(IGV_total * 100.0) / 100.0;
        double valor_venta = reserva.getTotalPago() - IGV_total;
        double valor_venta_round = Math.round(valor_venta * 100.0) / 100.0;

        Map<String, Object> params = new HashMap<>();
        params.put("nombres", reserva.getNombres());
        params.put("apellidos", reserva.getApellido());
        params.put("num_boleta", reserva.getIdReserva());

        params.put("fecha_reserva", reserva.getFechaReserva());
        params.put("hora_reserva", reserva.getHora());
        params.put("fecha_registro", reserva.getFechaRegistro());
        params.put("fecha_vencimiento", reserva.getFechaReserva());

        params.put("cantidad_personas", reserva.getCantPersonas());
        params.put("cantidad_acompaniante", reserva.getAcompaniante());

        params.put("acompaniante_price_total", acompaniante_price_total);
        params.put("acompaniante_price_unit", acompaniante_price_unit);
        params.put("personas_price_total", personas_price_total);
        params.put("personas_price_unit", personas_price_unit);

        params.put("IGV_perc", IGV_perc);
        params.put("IGV_total", IGV_total_round);
        params.put("valor_venta", valor_venta_round);

        params.put("tota_pago", reserva.getTotalPago());

        return StringSubstitutor.replace(TEMPLATE, params, "${", "}");
    }

    public String getVoucherEntradaTemplate(BoletaEntrada ticket) {
        return """
                <body>
                    <div style="width:600px; margin: auto; font-family:Arial, Helvetica, sans-serif">
                        <div >
                            <h2 style="text-align: center;">Boleta de venta electronica</h2>
                        </div>
                        <div  style="text-align: center;">
                            <img style="height: 78px; width: 176px;"src="https://iili.io/HMLqXJS.png" />
                            <p style="margin-top: 15px;">Ate 15494</p>
                            <p style="margin-top: -10px;">Local Puruchuco</p>
                        </div>
                        <hr style="min-width: 600px;height: 1px; margin: 1rem 0;color: inherit;background-color: currentColor;border: 0;">
                        <div  style="padding-bottom: 1em; display: flex;">
                            <div style="padding-right: 80px;">
                                <h5 style="font-weight: 900;font-size: 0.8em;">Cliente</h5>
                                <p style="font-size: 15px;">${nombres}</p>
                                <p style="font-size: 15px;">Fecha Reserva: ${fecha_reserva} ${hora_reserva} am</p>
                            </div>
                            <div style="padding-right: 40px;">
                                <h5 style="font-weight: 900;font-size: 0.8em;">N° de boleta:</h5>
                                <h5 style="font-weight: 900;font-size: 0.8em;">Fecha registro:</h5>
                                <h5 style="font-weight: 900;font-size: 0.8em;">Fecha vencimiento:</h5>
                            </div>
                            <div>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">103</h5>
                                <p>${fecha_registro}</p>
                                <p>26/02/2023</p>
                            </div>
                        </div>
                        <div style="padding-bottom: 40px;display: grid;grid-template-rows: minmax(220px,auto);">
                            <table style="table-layout: fixed;border-collapse: collapse;">
                                <thead style="border-top: solid 3px #000; border-bottom: 3px solid #000; height: 65px;">
                                <tr>
                                    <th style="padding-top: 30px;">Cant. Personas</th>
                                    <th style="padding-top: 30px;">Descripcion</th>
                                    <th style="padding-top: 30px;">Precio Unitario</th>
                                    <th style="padding-top: 30px;">Importe</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr style="height:40px;">
                                    <td style="text-align: center">1</td>
                                    <td style="text-align: center">Reserva de cumpleaños</td>
                                    <td style="text-align: right">0.00</td>
                                    <td style="text-align: right">0.00</td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">V. venta</th>
                                        <th style="text-align: right">0.00</th>
                                    </tr>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">IGV 18%</th>
                                        <th style="text-align: right">0.00</th>
                                    </tr>
                                    <tr style="height:30px;">
                                        <th></th>
                                        <th></th>
                                        <th style="text-align: right">Total </th>
                                        <th style="text-align: right">0.00</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div  style="border-top: solid 2px #000; padding-top: 20px;">
                            <div style="display:flex">
                                <div style="padding-right: 3em;">
                                    <h4>Condiciones y formas de pago</h4>
                                </div>
                                <div>
                                    <img style="height: 40px;width: 40px;"src="https://www.mrjoy.com.ec/wp-content/uploads/2022/03/favicon1.png"/>
                                </div>
                            </div>

                            <p></p>
                            <p>
                            Banco Banreserva
                            <br />
                            IBAN: DO XX 0075 XXXX XX XX XXXX XXXX
                            <br />
                            Código SWIFT: BPDODOSXXXX
                            </p>
                        </div>
                </body>""";
    }

}
