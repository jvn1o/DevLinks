<script setup>
import { ref, onMounted } from 'vue'
import { Editor, EditorContent } from '@tiptap/vue-3'
import StarterKit from '@tiptap/starter-kit'
import Image from '@tiptap/extension-image'

const props = defineProps({
  modelValue: String
})

const emit = defineEmits(['update:modelValue'])

const editor = ref(null)

onMounted(() => {
  editor.value = new Editor({
    content: props.modelValue,
    extensions: [
      StarterKit,
      Image.configure({
        inline: false,
        allowBase64: true,
      }),
    ],
    onUpdate({ editor }) {
      emit('update:modelValue', editor.getHTML())
    },
  })
})

function insertImage(event) {
  const file = event.target.files[0]
  if (!file) return

  const reader = new FileReader()
  reader.onload = () => {
    const base64 = reader.result
    editor.value
        .chain()
        .focus()
        .setImage({ src: base64 })
        .run()
  }
  reader.readAsDataURL(file)
}
</script>

<template>
  <div>
    <div class="mb-2">
      <input type="file" accept="image/*" @change="insertImage" />
    </div>
    <EditorContent v-if="editor" :editor="editor" class="border bg-white rounded" />
  </div>
</template>

<style scoped>
.ProseMirror {
  min-height: 300px;
  padding: 1rem;
  outline: none;
  white-space: pre-wrap;
  word-break: break-word;
}
</style>
