package com.mrjoi.messagejob.utils;

import com.mrjoi.messagejob.model.BoletaEntrada;
import com.mrjoi.messagejob.model.Reserva;
import org.apache.commons.text.StringSubstitutor;

import java.util.ArrayList;
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

        double acompaniante_price_unit = 6.0;
        double acompaniante_price_total = reserva.getAcompaniante() * acompaniante_price_unit;
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
    }

    public String getContratoReserva(Reserva reserva) {
        String TEMPLATE =  """
                  <html>
                        <head>
                      <style>
                          body {
                              background-color: #f7f7f7;
                              font-family: 'Roboto', sans-serif;
                              font-size: 16px;
                              color: #333;
                              margin: 0;
                          }
                  
                          header {
                              background-image: url(https://mrjoy.com.pe/wp-content/uploads/2019/09/headers-cumple.png);
                              padding: 40px 0;
                          }
                  
                          .header-left {
                              float: left;
                          }
                  
                          .header-left img {
                              height: 60px;
                          }
                  
                          .header-right {
                              float: right;
                              text-align: right;
                          }
                  
                          .header-right h1 {
                              font-size: 36px;
                              margin: 0;
                          }
                  
                          .header-right p {
                              margin: 5px 0;
                          }
                  
                          .container {
                              width: 80%;
                              margin: 0 auto;
                  
                          }
                  
                          .container h2,
                          h1 {
                              color: rgb(40, 56, 144);
                          }
                  
                          main {
                              padding: 40px 0;
                          }
                  
                          .details {
                              margin: 0 auto;
                              max-width: 600px;
                              margin-bottom: 20px;
                  
                          }
                  
                          .details h2 {
                              margin: 0;
                              font-size: 24px;
                          }
                  
                          table {
                              border-collapse: collapse;
                              width: 100%;
                          }
                  
                          table th,
                          table td {
                              padding: 10px;
                              text-align: left;
                              border: 1px solid #ccc;
                          }
                  
                          table th {
                              background: #1774b9;
                              color: #fff;
                          }
                  
                          table tfoot td {
                              text-align: right;
                          }
                  
                          table tfoot tr:first-child td {
                              border-top: 2px solid #ccc;
                          }
                  
                          table tfoot tr:last-child td {
                              border-bottom: 2px solid #ccc;
                          }
                  
                          footer {
                              background-color: #283890;
                              color: #fff;
                              padding: 20px 0;
                              text-align: center;
                          }
                  
                          footer p {
                              margin: 0;
                          }
                  
                          @media screen and (max-width: 768px) {
                              .header-left img {
                                  height: 40px;
                              }
                  
                              .header-right h1 {
                                  font-size: 28px;
                              }
                          }
                  
                          .header-right p {
                              font-size: 14px;
                          }
                  
                          p {
                              text-indent: 2em;
                          }
                      </style>
                  </head>
                  
                  <body>
                      <header>
                          <div class="container">
                              <div class="header-left" style="padding-top:50px">
                                  <img src="https://mrjoy.com.pe/wp-content/uploads/2019/09/headers-cumple.png" alt="Logo" />
                              </div>
                              <div class="header-right" style="padding-top:50px">
                  
                                  <p>123 Calle Principal</p>
                                  <p>Lima, Peru </p>
                                  <p>Teléfono: 01-456-7890</p>
                              </div>
                          </div>
                      </header>
                      <main>
                          <div class="container" style="padding-top:40px">
                              <div class="details">
                                  <h2>Detalles de la Reserva</h2>
                                  <p>Fecha Reserva: ${fecha_reserva}</p>
                                  <p>Hora : ${hora_reserva}</p>
                                  <p>Cliente : ${nombres} ${apellidos}</p>
                                  <p>Cantidad Invitados : ${cantidad_personas}</p>
                                  <p>n° de acompañantes : ${cantidad_acompaniante}</p>
                                  <p>Paquete Contratado : ${tipo_paquete}</p>
                              </div>
                  
                          </div>
                      </main>
                      <div class="container" style="">
                          <h1 style="text-align: center;">Contrato de Reserva para Cumpleaños</h1>
                  
                          <p>Este contrato (en adelante, el "Contrato") se realiza y se firma entre ${nombres} ${apellidos} (en adelante, el
                              "Cliente") y [Nombre del Proveedor] (en adelante, el "Proveedor") para reservar ${cantidad_personas}
                              personas para una celebración de cumpleaños en la fecha especificada por el Cliente.</p>
                  
                          <h2>1. Términos y Condiciones</h2>
                          <p>1.1 La reserva se realizará para la fecha ${fecha_reserva} y se llevará a cabo en el lugar indicado por
                              el Proveedor.</p>
                  
                          <p>1.2 La reserva incluirá espacio suficiente para alojar a ${cantidad_personas} personas.</p>
                  
                          <p>1.3 El Cliente será responsable de proporcionar la lista de invitados al Proveedor antes de la fecha del
                              evento.</p>
                  
                          <p>1.4 El Cliente se compromete a cumplir con las políticas y reglas del Proveedor en todo momento durante el
                              evento.</p>
                  
                          <p>1.5 El Proveedor se reserva el derecho de cancelar la reserva en cualquier momento y por cualquier motivo,
                              incluyendo pero no limitado a la violación de las políticas del proveedor, falta de pago, o cualquier otra
                              razón justificada.</p>
                  
                          <h2>2. Responsabilidades de las partes</h2>
                          <p>2.1 El Proveedor será responsable de proporcionar el espacio para la celebración del cumpleaños y de
                              garantizar que se cumplan todas las normas y regulaciones aplicables.</p>
                  
                          <p>2.2 El Proveedor no será responsable de ninguna lesión, daño o pérdida de propiedad durante el evento.</p>
                  
                          <p>2.3 El Cliente será responsable de cualquier daño a la propiedad del Proveedor causado por el Cliente o sus
                              invitados durante el evento.</p>
                  
                          <h2>3 .Términos de Pago</h2>
                          <p>3.1 El Cliente deberá pagar el ${tota_pago} como depósito para la reserva del evento.</p>
                  
                          <p>3.2 El saldo restante de la reserva deberá ser pagado en su totalidad antes de la fecha del evento.</p>
                  
                          <p>3.3 Si el Cliente no paga el saldo restante de la reserva antes de la fecha del evento, el Proveedor se
                              reserva el derecho de cancelar la reserva.</p>
                  
                          <h2>4. Política de Cancelación y Reembolso</h2>
                          <p>4.1 Si el Cliente cancela la reserva antes de 2 días de la fecha del
                              evento, el Proveedor reembolsará el depósito completo.</p>
                  
                          <p>4.2 Si el Cliente cancela la reserva dentro de 2 días de la fecha del
                              evento, el Proveedor no reembolsará el depósito.</p>
                  
                          <p>4.3 Si el Cliente no se presenta en la fecha del evento, el Proveedor no reembolsará el depósito.</p>
                          <h2>5. Otros Datos Relevantes</h2>
                          <p>5.1 Cualquier cambio en la fecha del evento debe ser notificado al Proveedor con al menos 7 días de anticipación.</p>
                          <p>5.2 El Cliente es responsable de proporcionar cualquier equipo, decoración, alimentos y bebidas necesarios
                              para el evento, a menos que se acuerde lo contrario en este Contrato.</p>
                          <p>5.3 Este Contrato se rige por las leyes del país o estado donde se lleva a cabo el evento.</p>
                  
                          <p>Firma del Cliente: ________</p>
                  
                          <p>Firma del Proveedor: ________</p>
                  
                          <p>Fecha: ________</p>
                      </div>
                  </body></html>""";

        double acompaniante_price_unit = 6.0;
        double acompaniante_price_total = reserva.getAcompaniante() * acompaniante_price_unit;
        double personas_price_total = reserva.getTotalPago() - acompaniante_price_total;
        double personas_price_unit = personas_price_total / reserva.getCantPersonas();
        double IGV_perc = 0.18;
        double IGV_total = reserva.getTotalPago() * 0.18;
        double IGV_total_round = Math.round(IGV_total * 100.0) / 100.0;
        double valor_venta = reserva.getTotalPago() - IGV_total;
        double valor_venta_round = Math.round(valor_venta * 100.0) / 100.0;

       ArrayList<String> paqueteList = new ArrayList<>();
        paqueteList.add("Paquete Nito");
        paqueteList.add("Paquete Mr. Joy");
        paqueteList.add("Paquete Mr. Joy");

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
        params.put("tipo_paquete", paqueteList.get(reserva.getIdPaquete()));

        params.put("IGV_perc", IGV_perc);
        params.put("IGV_total", IGV_total_round);
        params.put("valor_venta", valor_venta_round);

        params.put("tota_pago", reserva.getTotalPago());

        return StringSubstitutor.replace(TEMPLATE, params, "${", "}");
    }
}
