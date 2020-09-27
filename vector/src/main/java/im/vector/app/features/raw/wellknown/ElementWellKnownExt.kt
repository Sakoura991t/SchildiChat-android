/*
 * Copyright (c) 2020 New Vector Ltd
 *
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

package im.vector.app.features.raw.wellknown

import org.matrix.android.sdk.api.extensions.tryOrNull
import org.matrix.android.sdk.api.raw.RawService
import org.matrix.android.sdk.internal.util.awaitCallback

suspend fun RawService.getElementWellknown(userId: String): ElementWellKnown? {
    return tryOrNull { awaitCallback<String> { getWellknown(userId, it) } }
            ?.let { ElementWellKnownMapper.from(it) }
}

fun ElementWellKnown.isE2EByDefault() = elementE2E?.e2eDefault ?: riotE2E?.e2eDefault ?: true
