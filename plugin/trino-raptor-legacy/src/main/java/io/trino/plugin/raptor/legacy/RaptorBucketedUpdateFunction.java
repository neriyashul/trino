/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.raptor.legacy;

import io.trino.spi.Page;
import io.trino.spi.block.Block;
import io.trino.spi.block.RowBlock;
import io.trino.spi.block.SqlRow;
import io.trino.spi.connector.BucketFunction;

import static io.trino.spi.type.IntegerType.INTEGER;

public class RaptorBucketedUpdateFunction
        implements BucketFunction
{
    @Override
    public int getBucket(Page page, int position)
    {
        Block block = page.getBlock(0);
        SqlRow row = ((RowBlock) block.getUnderlyingValueBlock()).getRow(block.getUnderlyingValuePosition(position));
        return INTEGER.getInt(row.getRawFieldBlock(0), row.getRawIndex()); // bucket field of row ID
    }
}
