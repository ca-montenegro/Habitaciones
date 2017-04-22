/* 
 * Copyright (C) 2017 c.penaloza.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package co.edu.uniandes.csw.habitaciones.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessLogicException extends Exception {

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(Throwable cause) {
        super(cause);
    }

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
