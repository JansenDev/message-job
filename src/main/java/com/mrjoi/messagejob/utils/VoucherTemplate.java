package com.mrjoi.messagejob.utils;

import com.mrjoi.messagejob.model.BoletaEntrada;
import com.mrjoi.messagejob.model.Reserva;

public class VoucherTemplate {


    public VoucherTemplate() {
    }

    public String getVoucherReservaTemplate(Reserva reserva) {
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
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Cliente</h5>
                                <p style="font-size: 15px;">Arian Manuel Garcia Reynoso</p>
                                <p style="font-size: 15px;">Fecha Reserva: 26/02/2023 10:00 am</p>
                            </div>
                            <div style="padding-right: 40px;">
                                <h5 style="font-weight: 900;;font-size: 0.8em;">N° de boleta:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha registro:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha vencimiento:</h5>
                            </div>
                            <div>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">103</h5>
                                <p>10/02/2023</p>
                                <p>26/02/2023</p>
                            </div>
                        </div>
                        <div style="padding-bottom: 40px;display: grid;grid-template-rows: minmax(220px,auto);">
                            <table style="table-layout: fixed;border-collapse: collapse;">
                       \s
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
                        </div>\s
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
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Cliente</h5>
                                <p style="font-size: 15px;">Arian Manuel Garcia Reynoso</p>
                                <p style="font-size: 15px;">Fecha Reserva: 26/02/2023 10:00 am</p>
                            </div>
                            <div style="padding-right: 40px;">
                                <h5 style="font-weight: 900;;font-size: 0.8em;">N° de boleta:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha registro:</h5>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">Fecha vencimiento:</h5>
                            </div>
                            <div>
                                <h5 style="font-weight: 900;;font-size: 0.8em;">103</h5>
                                <p>10/02/2023</p>
                                <p>26/02/2023</p>
                            </div>
                        </div>
                        <div style="padding-bottom: 40px;display: grid;grid-template-rows: minmax(220px,auto);">
                            <table style="table-layout: fixed;border-collapse: collapse;">
                       \s
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
                        </div>\s
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
