/*
 * Copyright 2020 MariaDB Ab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mariadb.r2dbc.message.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.mariadb.r2dbc.client.Context;

/**
 * COM_STMT_CLOSE packet. See
 * https://mariadb.com/kb/en/3-binary-protocol-prepared-statements-com_stmt_close/
 */
public final class ClosePreparePacket implements ClientMessage {

  private final int statementId;

  public ClosePreparePacket(int statementId) {
    this.statementId = statementId;
  }

  @Override
  public ByteBuf encode(Context context, ByteBufAllocator allocator) {
    ByteBuf buf = allocator.ioBuffer();
    buf.writeByte(0x19);
    buf.writeIntLE(statementId);
    return buf;
  }
}
